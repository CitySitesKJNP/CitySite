$(document).ready(function () {
    $("#create-an-element").click(function () {
        $(this).after('<br><input id="image" name="urls" type="url" placeholder="Please Enter an Image URL...">');
    });
});