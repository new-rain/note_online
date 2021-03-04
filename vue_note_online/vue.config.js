const webpack = require('webpack')

module.exports = {
    devServer: {
        port: 8080
    },
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                $: 'jquery',
                jQuery: 'jquery',
                'window.jQuery': 'jquery',
                'window.$': 'jquery'
            })
        ]
    },
    transpileDependencies: [
        'vue-echarts',
        'resize-detector'
    ]
}