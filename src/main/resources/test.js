function download(id) {
    currentScope.download(id)
        .then(function (data) {
            console.log('tipo di data', (typeof data));
            if ((typeof data) == 'string') {
                // Not Managed Server error
                _manageError(data, 0);
                return;
            }
            console.log('if condition data: ', data);
            var blob = new Blob([data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
            var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
            var url = urlCreator.createObjectURL(blob);
            var link = document.createElement("a");
            link.setAttribute("href", url);
            link.setAttribute("download", data.filename);
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link)
        }, _manageError);
};