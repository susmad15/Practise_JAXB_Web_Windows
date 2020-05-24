class FetchData {
    
    getRepresentatives() {
        var url = "RetrieveRepresentativesOfCountry";
        var country = document.getElementById("countrySelect").value;
        
        fetch(url, {method: "POST", body : country}).then(response => response.json()).then(data => this.setupSelect(data, "repSelect"));
    }
    
    getCustomers() {
        var url = "RetrieveCustomersOfRepresentative";
        var rep = document.getElementById("repSelect").value;
        
        fetch(url, {method: "POST", body : rep}).then(response => response.json()).then(data => this.setupSelect(data, "customerSelect"));
    }
    
    setupSelect(json, selectObject) {
        var selectString = "";
        
        for(var i = 0; i<json.length; i++) {
            selectString += "<option>" + json[i].name + "</option>";
        }
        
        document.getElementById(selectObject).innerHTML = selectString;
    }
}