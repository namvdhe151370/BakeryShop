$("document").ready(function () {
    $("#filterTable").dataTable({
        "searching": true
    });
    //Get a reference to the new datatable
    var table = $('#filterTable').DataTable();
    //Take the category filter drop down and append it to the datatables_filter div. 
    //You can use this same idea to move the filter anywhere withing the datatable that you want.

    $("#status").insertBefore($("#filterTable_filter.dataTables_filter"));
    $("#type").insertBefore($("#filterTable_filter.dataTables_filter"));

    //Get the column index for the Category column to be used in the method below ($.fn.dataTable.ext.search.push)
    //This tells datatables what column to filter on when a user selects a value from the dropdown.
    //It's important that the text used here (Category) is the same for used in the header of the column to filter

    var statusIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Status") {
            statusIndex = i;
            return false;
        }
    });
    var typeIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Type") {
            typeIndex = i;
            return false;
        }
    });


    //Use the built in datatables API to filter the existing rows by the Category column

    $.fn.dataTable.ext.search.push(
            function (settings, data, dataIndex) {
                var selectedItem = $('#status').val()
                var status = data[statusIndex];
                if (selectedItem === "" || status.includes(selectedItem)) {
                    return true;
                }
                return false;
            }
    );
    $.fn.dataTable.ext.search.push(
            function (settings, data, dataIndex) {
                var selectedItem = $('#type').val()
                var type = data[typeIndex];
                if (selectedItem === "" || type.includes(selectedItem)) {
                    return true;
                }
                return false;
            }
    );



    //Set the change event for the Category Filter dropdown to redraw the datatable each time
    //a user selects a new filter.

    $("#status").change(function (e) {
        table.draw();
    });
    $("#type").change(function (e) {
        table.draw();
    });


    table.draw();
});
  $("body").append('<div class="fixed-button active"><a href="/src/admin/addsetting" target="_blank" class="btn btn-md btn-primary"><i class="fa fa-plus" aria-hidden="true"></i>New Setting</a> </div>');
        var $window = $(window), nav = $(".fixed-button");

