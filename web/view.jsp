<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 20.05.2020, 22:14:45
    Author     : markus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practise JAXB Web 2</title>
    </head>
    <body>
        <h1>Practise JAXB Web 2</h1>

        <select id="countrySelect" size="10" style="margin-right: 50px" onchange="loadReps()">

            <c:forEach var="country" items="${countries}">

                <option>${country.name}</option>

            </c:forEach>

        </select>

        <select id="repSelect" size="10" style="margin-right: 50px" onchange="loadCustomers()">

        </select>

        <select id="customerSelect" size="10" style="margin-right: 50px">

        </select>

        <script type="text/javascript" src="FetchData.js"></script>
        <script type="text/javascript">

            var fd = new FetchData();

            const loadReps = () => {
                fd.getRepresentatives();
            }

            const loadCustomers = () => {
                fd.getCustomers();
            }

        </script>


    </body>
</html>
