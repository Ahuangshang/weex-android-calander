var fs = require('fs'), stat = fs.stat;

//拷贝文件
var copy = function (src, dst) {
    fs.readdir(src, function (err, paths) {
        if (err) {throw err;}

        paths.forEach(function (path) {
            var _src = src + '/' + path,
                _dst = dst + '/' + path,
                readable, writable;
            stat(_src, function (err, st) {
                if (err) {
                    throw err;
                }
                // 判断是否为文件
                if (st.isFile()) {
                    //*.web.js不拷贝
                    if(path.indexOf("web.js")<0){
                        readable = fs.createReadStream(_src);
                        writable = fs.createWriteStream(_dst);
                        readable.pipe(writable);
                    }
                }
                else if (st.isDirectory()) {
                    exists(_src, _dst, copy);
                }
            });
        });
    });
};

//是否存在文件/文件夹
var exists = function (src, dst, callback) {
    fs.exists(dst, function (exists) {
        if (exists) {
            callback(src, dst);
        }
        else {
            fs.mkdir(dst, function () {
                callback(src, dst);
            });
        }
    });
};

//删除文件夹
var rmdirSync = (function () {
    function iterator(url, dirs) {
        var stat = fs.statSync(url);
        if (stat.isDirectory()) {
            dirs.unshift(url);//收集目录
            inner(url, dirs);
        } else if (stat.isFile()) {
            fs.unlinkSync(url);//直接删除文件
        }
    }

    function inner(path, dirs) {
        var arr = fs.readdirSync(path);
        for (var i = 0, el; el = arr[i++];) {
            iterator(path + "/" + el, dirs);
        }
    }

    return function (dir, cb) {
        cb = cb || function () {
            };
        var dirs = [];

        try {
            iterator(dir, dirs);
            for (var i = 0, el; el = dirs[i++];) {
                fs.rmdirSync(el);
            }
            cb()
        } catch (e) {
            //如果文件或目录本来就不存在，fs.statSync会报错，当成没有异常发生
            e.code === "ENOENT" ? cb() : cb(e);
        }
    }
})();


var src = "./dist";
var dst = "./publish";

//先删除publish目录，之后在进行拷贝
rmdirSync(dst, function (err) {
    exists(src, dst, function () {
        copy(src, dst);
    });
});

