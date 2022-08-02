$("document").ready(function () {
    $("#filterTable").dataTable({
        "searching": true
    });
//Get a reference to the new datatable
    var table = $('#filterTable').DataTable();
//Take the category filter drop down and append it to the datatables_filter div. 
//You can use this same idea to move the filter anywhere withing the datatable that you want.
    $("#gender").insertBefore($("#filterTable_filter.dataTables_filter"));
    $("#status").insertBefore($("#filterTable_filter.dataTables_filter"));
    $("#role").insertBefore($("#filterTable_filter.dataTables_filter"));
//Get the column index for the Category column to be used in the method below ($.fn.dataTable.ext.search.push)
//This tells datatables what column to filter on when a user selects a value from the dropdown.
//It's important that the text used here (Category) is the same for used in the header of the column to filter
    var categoryIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Gender") {
            categoryIndex = i;
            return false;
        }
    });
    var statusIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Status") {
            statusIndex = i;
            return false;
        }
    });
    var roleIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Role") {
            roleIndex = i;
            return false;
        }
    });

//Use the built in datatables API to filter the existing rows by the Category column
    $.fn.dataTable.ext.search.push(
            function (settings, data, dataIndex) {
                var selectedItem = $('#gender').val()
                var category = data[categoryIndex];
                if (selectedItem === "" || category.includes(selectedItem)) {
                    return true;
                }
                return false;
            }
    );
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
                var selectedItem = $('#role').val()
                var role = data[roleIndex];
                if (selectedItem === "" || role.includes(selectedItem)) {
                    return true;
                }
                return false;
            }
    );

//Set the change event for the Category Filter dropdown to redraw the datatable each time
//a user selects a new filter.
    $("#gender").change(function (e) {
        table.draw();
    });
    $("#status").change(function (e) {
        table.draw();
    });
    $("#role").change(function (e) {
        table.draw();
    });

    table.draw();
});

$("body").append('<div class="fixed-button active"><a href="/src/user/addnew" target="_blank" class="btn btn-md btn-primary"><i class="fa fa-user-plus" aria-hidden="true"></i>New Account</a> </div>');
var $window = $(window), nav = $(".fixed-button");

