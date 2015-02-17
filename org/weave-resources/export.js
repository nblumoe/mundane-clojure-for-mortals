hljs.configure({
  tabReplace: '  '
})
hljs.initHighlighting();

$(document).ready(function() {
  $('pre.src').each(function(i, block) {
    var $this = $(this);
    var srcClass = $this.attr('class').match(/src-(.*)/)[1];
    $this.addClass(srcClass);
    hljs.highlightBlock(block);
  });
});
