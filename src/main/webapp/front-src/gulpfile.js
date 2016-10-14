var gulp = require('gulp');
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');
var clean = require('gulp-clean');

var paths = {
    style: './sass/style.scss',
    toCopy: ['./bower_components/font-awesome/fonts/*', './bower_components/material-kit/assets/js/*', './fonts/material-icons.woff2'],
    dest: '../assets'
};

gulp.task('clean', function(){
    return gulp.src(paths.dest, {read: false})
        .pipe(clean({force: true}));
});

gulp.task('copy', ['clean'], function() {
    return gulp.src(paths.toCopy)
        .pipe(gulp.dest(function(test) {
            // to save folder name (css, fonts, ...)
            var pathParts = test.path.split('/');
            return  paths.dest + '/' + pathParts[pathParts.length-2]
        }));
});

gulp.task('sass', ['copy'], function () {
    return gulp.src(paths.style)
        .pipe(sass().on('error', sass.logError))
        .pipe(cleanCSS())
        .pipe(gulp.dest(paths.dest + '/css'));
});

gulp.task('watch', ['sass'], function () {
    gulp.watch(paths.style, ['sass']);
});

gulp.task('default', ['clean', 'copy', 'sass', 'watch']);