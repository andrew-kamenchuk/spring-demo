(function() {
    "use strict";

    const $ = require("jquery");

    const filtersForm = $("form[name='filters']");

    filtersForm.find("input:checkbox").on("change", function() {
        filtersForm.submit();
    });
}());
