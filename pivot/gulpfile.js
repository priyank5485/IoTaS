'use strict';

var gulp = require('gulp');
var runSequence = require('run-sequence');

var laborer = require('laborer');

gulp.task('style', laborer.taskStyle());
gulp.task('icons', laborer.taskIcons());

var dontCache = !process.env['DONT_CACHE'];
var skipLibCheck = !process.env['DO_LIB_CHECK'];
gulp.task('client:tsc', laborer.taskClientTypeScript({ cache: dontCache, declaration: true, skipLibCheck: skipLibCheck }));
gulp.task('server:tsc', laborer.taskServerTypeScript({ cache: dontCache, declaration: true, skipLibCheck: skipLibCheck }));

gulp.task('client:bundle', laborer.taskClientPack());

gulp.task('clean', laborer.taskClean());

gulp.task('all', function(cb) {
  laborer.failOnError();
  runSequence(
    'clean' ,
    ['style', 'icons'],
    ['server:tsc', 'client:tsc'],
    'client:bundle',
    cb
  );
});

gulp.task('stats', function(cb) {
  laborer.showStats();
  gulp.start('all');
});

gulp.task('all-minus-bundle', function(cb) {
  runSequence(
    'clean' ,
    ['style', 'icons'],
    ['server:tsc', 'client:tsc'],
    cb
  );
});

gulp.task('watch', ['all-minus-bundle'], function() {
  gulp.watch('./src/client/**/*.scss', ['style']);
  gulp.watch('./src/client/**/*.svg', ['icons']);

  gulp.watch(['./src/common/**/*.ts', './src/client/**/*.{ts,tsx}', './assets/icons/**'], ['client:tsc']);

  gulp.watch(['./src/common/**/*.ts', './src/server/**'], ['server:tsc']);

  if (!process.env['NO_GULP_WATCH_PACK']) {
    laborer.clientPackWatch()
  }
});

gulp.task('default', ['all']);
