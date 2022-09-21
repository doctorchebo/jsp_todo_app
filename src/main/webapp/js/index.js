$('#targetDate').datepicker({
    format : 'dd/mm/yyyy'
});
$('#startDate').datepicker({
    format : 'dd/mm/yyyy'
});
$('#title').keyup(function(){
    let title = $('#title').val();
    if(title.length>=3){
        $('#detail').html('')
        $('.list-group').css('display', 'block')
        $.ajax({
            url: '/TodoList/searchTitles',
            method: 'POST',
            data: {title: title},
            success: function(data){
                $('.list-group').html(data);
            }
        })
    }
    if(title.length==0){
        $('.list-group').css('display', 'none')
    }
    $(document).on('click','.list-group-item', function(){
        let title = $(this).text();
        $('#title').val(title);
        hideList();
        $('#title').focus();
    })
    function hideList(){
        $('.list-group').css('display', 'none')
    }
    $('#title').focusout(function(){
        setTimeout(function(){
            hideList();
        }, 100)
    })
})