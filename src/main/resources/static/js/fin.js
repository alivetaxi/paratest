function closeMsgModal() {
	$("#msgModal").removeClass("d-block");
}

var showMsgModal = function (msg) {
	$("#msgModalContent").text(msg);
	$("#msgModal").addClass("d-block");
}