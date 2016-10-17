var gulp = require('gulp');
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');
var clean = require('gulp-clean');

var paths = {
    styleMainFile: './sass/main.scss',
    styleFiles: './sass/*',
    toCopy: ['./bower_components/font-awesome/fonts/*', './bower_components/material-kit/assets/js/*', './fonts/material-icons.woff2'],
    dest: '../assets'
};

gulp.task('clean', function(){
    return gulp.src(paths.dest, {read: false})
        .pipe(clean({force: true}));
});

gulp.task('copy', ['clean'], function() {
    return gulp.src(paths.toCopy)
        .pipe(gulp.dest(function(file) {
            // to keep directory name ("fonts" or "js")
            var pathParts = file.path.split('/');
            return paths.dest + '/' + pathParts[pathParts.length-2]
        }));
});

gulp.task('sass', ['copy'], function () {
    return gulp.src(paths.styleMainFile)
        .pipe(sass().on('error', sass.logError))
        .pipe(cleanCSS())
        .pipe(gulp.dest(paths.dest + '/css'));
});

gulp.task('watch', ['sass'], function () {
    gulp.watch(paths.styleFiles, ['sass']);
});

gulp.task('default', ['clean', 'copy', 'sass']);