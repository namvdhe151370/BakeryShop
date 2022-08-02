$("document").ready(function () {
    $("#filterTable").dataTable({
        "searching": true
    });
    //Get a reference to the new datatable
    var table = $('#filterTable').DataTable();
    //Take the category filter drop down and append it to the datatables_filter div. 
    //You can use this same idea to move the filter anywhere withing the datatable that you want.

    $("#status").insertBefore($("#filterTable_filter.dataTables_filter"));
    $("#product").insertBefore($("#filterTable_filter.dataTables_filter"));
    $("#rateStar").insertBefore($("#filterTable_filter.dataTables_filter"));
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
    var productIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Product Name") {
            productIndex = i;
            return false;
        }
    });
    var starIndex = 0;
    $("#filterTable th").each(function (i) {
        if ($($(this)).html() == "Rated Star") {
            starIndex = i;
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
                var selectedItem = $('#product').val()
                var product = data[productIndex];
                if (selectedItem === "" || product.includes(selectedItem)) {
                    return true;
                }
                return false;
            }
    );
    $.fn.dataTable.ext.search.push(
            function (settings, data, dataIndex) {
                var selectedItem = $('#rateStar').val()
                var star = data[starIndex];
                if (selectedItem === "" || star.includes(selectedItem)) {
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
    $("#product").change(function (e) {
        table.draw();
    });
    $("#rateStar").change(function (e) {
        table.draw();
    });

    table.draw();
});

