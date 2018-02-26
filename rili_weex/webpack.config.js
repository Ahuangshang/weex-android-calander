const pathTo = require('path');
let webpack = require('webpack');
let glob = require("glob");
let Copy = require('copy-webpack-plugin');
let HtmlWebpackPlugin = require('html-webpack-plugin');
let bannerPlugin = new webpack.BannerPlugin(
  {
    banner: '// { "framework": "Vue" }\n',
    raw: true,
    exclude: 'Vue'
  }
);
//  文件拷贝插件,将图片和字体拷贝到dist目录
let copyPlugin = new Copy([
  {from: './src/image', to: "./image"},
]);
let plugins = [
  bannerPlugin, copyPlugin
];

// 遍历文件入口,动态生成入口
function getEntries() {
  let entryFiles = glob.sync('./src/entry/**', {'nodir': true});
  let entries = {};
  for (let i = 0; i < entryFiles.length; i++) {
    let filePath = entryFiles[i];
    let filename = filePath.split('entry/')[1];
    filename = filename.substr(0, filename.lastIndexOf('.'));
    entries[filename] = filePath;
    plugins[plugins.length] = new HtmlWebpackPlugin({
      filename: filename + '.html',
      template: './template.html',
      inject: true,              // js插入位置
      chunks: [filename], // 每个html引用的js模块
    });

  }
  return entries;
}


// 生成webpack配置
function getBaseConfig(pathname) {
  return {
    entry: getEntries(),
    output: {
      path: pathTo.join(__dirname, 'dist/' + pathname),
    },
    module: pathname === 'web' ? {
        // webpack 2.0
        rules: [{
          test: /\.js$/,
          use: [{
            loader: 'babel-loader'
          }],
          exclude: /node_modules(?!\/.*(weex).*)/
        }, {
          test: /\.vue(\?[^?]+)?$/,
          use: [{
            loader: 'vue-loader',
            options: {
              compilerModules: [
                {
                  postTransformNode: el => {
                    el.staticStyle = `$processStyle(${el.staticStyle})`
                    el.styleBinding = `$processStyle(${el.styleBinding})`
                  }
                }
              ]
            }
          }]
        }]
      }
      : {
        rules: [{
          test: /\.js$/,
          use: [{
            loader: 'babel-loader'
          }],
          exclude: /node_modules(?!\/.*(weex).*)/
        }, {
          test: /\.vue(\?[^?]+)?$/,
          use: [{
            loader: 'weex-loader'
          }]
        }, {
          test: /\.we(\?[^?]+)?$/,
          use: [{
            loader: 'weex-loader'
          }]
        }]
      },

    plugins: pathname === 'web' ? plugins : [bannerPlugin, copyPlugin]
  }
}

//*.web.js
let webConfig = getBaseConfig("web");
webConfig.output.filename = 'js/[name].web.js';


//*.weex.js
let weexConfig = getBaseConfig("weex");
weexConfig.output.filename = '[name].weex.js';

module.exports = [webConfig, weexConfig];