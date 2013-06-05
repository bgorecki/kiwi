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
                    <form class="form">
                    <c:choose>
                        <c:when test="${empty loty}">
                            Nie znaleziono zamienników na ten loty
                        </c:when>
                        <c:otherwise>
                            <br><br>
                            <c:forEach var="i" items="${loty}" varStatus="loop">
                                <fieldset class="grey-bg">
                                    <legend>Propozycja #${loop.count}</legend>
                                    <ul class="planning no-margin">
                                        <li class="planning-header">
                                            <span><b>Users</b></span>
                                            <ul>
                                                <li class="at-0">0</li>
                                                <li class="at-1">1</li>
                                                <li class="at-2">2</li>
                                                <li class="at-3">3</li>
                                                <li class="at-4">4</li>
                                                <li class="at-5">5</li>
                                                <li class="at-6">6</li>
                                                <li class="at-7">7</li>
                                                <li class="at-8">8</li>
                                                <li class="at-9">9</li>
                                                <li class="at-10">10</li>
                                                <li class="at-11">11</li>
                                                <li class="at-12">12</li>
                                                <li class="at-13">13</li>
                                                <li class="at-14">14</li>
                                                <li class="at-15">15</li>
                                                <li class="at-16">16</li>
                                                <li class="at-17">17</li>
                                                <li class="at-18">18</li>
                                                <li class="at-19">19</li>
                                                <li class="at-20">20</li>
                                                <li class="at-21">21</li>
                                                <li class="at-22">22</li>
                                                <li class="at-23">23</li>
                                                <li class="at-24">24</li>
                                            </ul>
                                        </li>
                                        <c:forEach var="j" items="${i}" varStatus="jLoop">
                                            <li>
                                                <a href="#"><img src="images/icons/fugue/control-double.png" width="16"
                                                                 height="16"> ${j.lotniskoByWylot.miasto}</a>
                                                <ul>
                                                    <li class="from-${j.godzinaWylotu.hours} to-${j.godzinaPrzylotu.hours}"><a href="#" title="${j.lotniskoByWylot.miasto} -> ${j.lotniskoByPrzylot.miasto} <fmt:formatDate value="${j.godzinaWylotu}" pattern="HH:mm"/> ->  <fmt:formatDate value="${j.godzinaPrzylotu}" pattern="HH:mm"/>"
                                                                                                                               class="with-tip event-blue">
                                                            ${j.lotniskoByWylot.miasto} -> ${j.lotniskoByPrzylot.miasto} <fmt:formatDate value="${j.godzinaWylotu}" pattern="HH:mm"/> ->  <fmt:formatDate value="${j.godzinaPrzylotu}" pattern="HH:mm"/></a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </fieldset>

                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </form>
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