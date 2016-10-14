var gulp = require('gulp');
var sass = require('gulp-sass');
var copy = require('gulp-contrib-copy');
var clean = require('gulp-clean');

var paths = {
    style: './sass/main.scss',
    toCopy: ['./bower_components/font-awesome/fonts/*', './bower_components/material-kit/assets/js/*'],
    dest: '../assets'
};

gulp.task('clean', function(){
    return gulp.src(paths.dest, {read: false})
        .pipe(clean());
});


gulp.task('copy', function() {
    gulp.src(paths.toCopy)
        .pipe(copy())
        .pipe(gulp.dest(paths.dest));

    return gulp.src(paths.toCopy, {base: './source/'})
        .pipe(gulp.dest('./public/assets/'));
});

gulp.task('sass', function () {
    return gulp.src(paths.style)
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(paths.dest + '/css'));
});

gulp.task('watch', function () {
    gulp.watch(paths.style, ['sass']);
});

gulp.task('default', ['clean', 'copy', 'sass', 'watch']);