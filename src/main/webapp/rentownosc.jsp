<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp"/>

<article class="container_12">
    <section class="grid_12">
        <div class="block-border">
            <div class="block-content">

                <h1>Rentowność lotu</h1>

                <script src="<c:url value="js/charts/Chart.js"/>"></script>
                <meta name = "viewport" content = "initial-scale = 1, user-scalable = no">
                <canvas id="canvas" height="450" width="1000"></canvas>


                <script>

                    var lineChartData = {
                        labels : [<c:forEach var="i" items="${days}">'${i} - ${counter[i]/max*100} %',</c:forEach>],
                        datasets : [
                            {
                                fillColor : "rgba(220,220,220,0.5)",
                                strokeColor : "rgba(220,220,220,1)",
                                pointColor : "rgba(220,220,220,1)",
                                pointStrokeColor : "#fff",
                                data : [<c:forEach var="i" items="${days}">${counter[i]},</c:forEach>]
                            },
                        ]

                    }

                    var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);

                </script>       <br><br>
                <c:if test="${rent < 50}">
                    Lot jest nierentowny, powinineś rozważyć likwidację lotu lub zmianę maszyny obsługującej trasę lotu.
                </c:if>
                <c:if test="${rent > 50}">
                    Lot jest rentowny.
                </c:if>
                <c:if test="${rent > 90}">
                    Powinieneś rozważyć na zmianę maszyny obsługującej lot na większą.
                </c:if>
            </div>
        </div>
    </section>
</article>

<jsp:include page="/WEB-INF/view-elements/footer.jsp"/>