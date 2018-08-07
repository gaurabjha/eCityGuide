var imgArray = [ 'images/SlideShow/pic1.jpg', 'images/SlideShow/pic2.jpg',
		'images/SlideShow/pic3.jpg', 'images/SlideShow/pic4.jpg',
		'images/SlideShow/pic5.jpg', 'images/SlideShow/pic6.jpg',
		'images/SlideShow/pic7.jpg', 'images/SlideShow/pic8.jpg',
		'images/SlideShow/pic11.jpg', 'images/SlideShow/pic12.jpg' ];

curIndex = 0;
imgDuration = 3000;
function slideShow() {
	setTimeout(function(){document.getElementById('slider').src = imgArray[curIndex];},1000);
	curIndex++;
	if (curIndex == imgArray.length) {
		curIndex = 0;
	}
	setTimeout("slideShow()",3000);
}
slideShow();