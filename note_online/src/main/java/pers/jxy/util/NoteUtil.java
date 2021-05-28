package pers.jxy.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import pers.jxy.entity.NoteBook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:靳新宇
 * @date : 12-01 10:39
 */
@Component
@PropertySource("classpath:application.yml")
public class NoteUtil {

    @Value("${note.path}")
    private String note_path;

    @Value("${note.note}")
    private String note;

    private NoteUtil() {

    }

    /**
     * 将笔记内容通过IO创建md文件，文件名为笔记名加笔记编号，并保存内容，返回笔记路径
     *
     * @param name
     * @param arthicle
     * @return
     */
    public String saveArthicle(String name, String arthicle) {
        String path = note_path + note + "/" + name + ".md ";
        Boolean res = writeNote(arthicle, path);
        if (res) {
            return "/" + name + ".md ";
        } else {
            return null;
        }
    }

    /**
     * 向md文件中写入内容
     */
    public Boolean writeNote(String arthicle, String path) {
//        File file = new File(note_path + note + "/"+path);
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(arthicle);
            fileWriter.flush();
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                    return Boolean.TRUE;
                } catch (IOException e) {
                    e.printStackTrace();
                    return Boolean.FALSE;
                }
            }
        }
    }

    /**
     * 根据路径读取笔记，并返回数据
     */
    public String getArthicle(String path) {
        String arthicle = "";
        File file = new File(note_path + note + path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            char[] ch = new char[1024];
            int data;
            while ((data = br.read(ch)) != -1) {
                arthicle += new String(ch, 0, data);
            }
            return arthicle;
        } catch (IOException e) {
            e.printStackTrace();
            return arthicle;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将md格式转换为HTML格式
     */
    public String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public String markdownToHtmlExtensions(String markdown) {
        Set<Extension> headingAnchorExtension = Collections.singleton(HeadingAnchorExtension.create());
        List<Extension> tableExtensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tableExtensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(headingAnchorExtension).extensions(tableExtensions).attributeProviderFactory(new AttributeProviderFactory() {
            @Override
            public AttributeProvider create(AttributeProviderContext context) {
                return new CustomAttributeProvider();
            }
        }).build();
        return renderer.render(document);
    }

    /**
     * 处理标签属性
     */
    static class CustomAttributeProvider implements AttributeProvider {

        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "md_table");
            }
        }
    }

    /**
     * 笔记按年-日期分类
     *
     * @param lists:分类排序数据;
     * @param timeOrder:正序/ 反序
     * @return
     */
    public List<Map<String, List>> getGroupByTime(List<List<NoteBook>> lists, Boolean timeOrder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List> map = new HashMap();
        for (int i = 0; i < lists.size(); i++) {
            String year = sdf.format(lists.get(i).get(0).getBCreateTime()).split("-")[0];
            if (map.containsKey(year)) {
                map.get(year).add(lists.get(i));
            } else {
                map.put(year, new ArrayList<>());
                map.get(year).add(lists.get(i));
            }
        }
        List<Integer> years = new ArrayList<>();
        for (String key : map.keySet()) {
            Integer year = Integer.valueOf(key);
            years.add(year);
        }
        Collections.sort(years);
        if (timeOrder) {
            Collections.reverse(years);
        }
        List<Map<String, List>> results = new ArrayList<>();
        for (int i = 0; i < years.size(); i++) {
            String key = String.valueOf(years.get(i));
            HashMap<String, List> m = new HashMap<>();
            m.put(key, map.get(key));
            results.add(m);
        }
        return results;
    }


    /**
     * 删除文件
     */
    public Boolean deleteMd(String filePath) {
        Path path = Paths.get(filePath);
        Boolean result = false;
        try {
            result = Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
            result = Boolean.FALSE;
        } finally {
            return result;
        }
    }

    /**
     * 清空md文件
     */
    public Boolean clearFile(String fileName) {
        File file = new File(note_path + note + fileName);
        System.out.println(file);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
