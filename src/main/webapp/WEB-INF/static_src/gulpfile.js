"use strict";

const gulp = require("gulp");
const path = require("path");

const ROOT = path.dirname(module.filename);

gulp
    .task("jquery", function() {
        const browserifyStr = require("browserify-string");
        const source = require("vinyl-source-stream");
        const uglify = require("gulp-uglify");
        const buffer = require("gulp-buffer");

        const b = browserifyStr(`
            var $ = global.$ = global.jQuery = require("jquery");
            require("bootstrap");
            module.exports = $;
        `);

        const bundleStream = b.require("jquery").bundle();

        return bundleStream
            .pipe(source("jquery.min.js"))
            .pipe(buffer())
            .pipe(uglify())
            .pipe(gulp.dest(ROOT + "/../static/tp"))
    })

    .task("bootstrap.css", function() {
        return gulp.src(ROOT + "/node_modules/bootstrap/dist/{css,fonts}/*")
            .pipe(gulp.dest(ROOT + "/../static/tp/bootstrap"));
    })

    .task("fontawesome", function() {
        return gulp.src(ROOT + "/node_modules/font-awesome/{css,fonts}/*")
            .pipe(gulp.dest(ROOT + "/../static/tp/font-awesome"));
    })

    .task("polyfill", function() {
        const rename = require("gulp-rename");

        return gulp.src(ROOT + "/node_modules/babel-polyfill/browser.js")
            .pipe(rename("polyfill.js"))
            .pipe(gulp.dest(ROOT + "/../static/tp"));
    })

    .task("third_party", ["jquery", "bootstrap.css", "fontawesome", "polyfill"]);

gulp.task("app.js", function() {
    const browserify = require("browserify");
    const babelify = require("babelify");
    const source = require("vinyl-source-stream");
    const uglify = require("gulp-uglify");
    const buffer = require("gulp-buffer");

    return browserify(ROOT + "/js/app.js")
        .transform(babelify, { presets: ["es2015"] })
        .external("jquery")
        .bundle()
        .pipe(source("app.min.js"))
        .pipe(buffer())
        .pipe(uglify())
        .pipe(gulp.dest(ROOT + "/../static/js"));
});

gulp.task("app.css", function() {
    const less = require("gulp-less");
    const cssmin = require("gulp-cssmin");
    const rename = require("gulp-rename");

    return gulp.src(ROOT + "/less/app.less")
        .pipe(less())
        .pipe(cssmin())
        .pipe(rename("styles.css"))
        .pipe(gulp.dest(ROOT + "/../static/css"));
});


gulp.task("default", ["third_party", "app.js", "app.css"]);
