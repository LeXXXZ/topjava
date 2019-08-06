$(function () {
    makeEditable({
            ajaxUrl: "ajax/profile/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
});

function filter() {
    $.ajax({
        url: "ajax/profile/meals/filter/",
        type: "GET",
        data:  $("#filter").serialize()
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
        successNoty("Filtered");
    });
}

function resetInput() {
    $("#filter")[0].reset();
}