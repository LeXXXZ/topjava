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
    /*$.get("ajax/profile/meals/filter/"
        , $("#filter").serialize()
        , function (data) {
            context.datatableApi.clear().rows.add(data).draw();
        }
    );*/
    $.ajax({
        url: "ajax/profile/meals/filter/",
        type: "GET",
        data: $("#filter").serialize()
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}

function resetInput() {
    $("#filter")[0].reset();
    getAll();
}

function updateTable() {
    filter();
}