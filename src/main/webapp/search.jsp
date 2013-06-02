<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
<script src="/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<style>
    .ui-autocomplete-category {
        font-weight: bold;
        padding: .2em .4em;
        margin: .8em 0 .2em;
        line-height: 1.5;
    }

</style>
<script>
    $.widget( "custom.catcomplete", $.ui.autocomplete, {
        _renderMenu: function( ul, items ) {
            var that = this,
                    currentCategory = "";
            $.each( items, function( index, item ) {
                if ( item.category != currentCategory ) {
                    ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                    currentCategory = item.category;
                }
                that._renderItemData( ul, item );
            });
        }
    });
</script>
<script>
    $(function() {
        var data = [
            <c:forEach var="i" items="${flights}">{label: "${i.miasto} - ${i.nazwa}", category: "${i.panstwo}"},</c:forEach>
        ];

        $( "#search, #search2" ).catcomplete({
            delay: 0,
            source: data
        });
    });

    $(function() {
        $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+11M", "dateFormat": "dd/mm/yy" });
    });
</script>


<article class="container_12">
    <section class="grid_12">
        <div class="block-border">
            <form class="block-content form" id="simple_form" method="get" action="">
                <input type="hidden" name="sent" value="1"/>
            <h1>Wyszukiwarka lotów</h1>

                <c:if test="${not empty errors}">
                    <ul class="message error no-margin">
                        <c:forEach items="${errors}" var="i">
                            <li>${i.value}</li>
                        </c:forEach>
                    </ul>
                </c:if>

            <fieldset class="grey-bg required">
                <legend>Podaj lotniska</legend>
                <p class="grid_6">

                    <label for="search">Lotnisko początkowe</label>
                    <c:if test="${not empty errors.from}"><span class="error relative full-width"></c:if>
                    <input type="text" name="from" id="search" value="${param.from}" class="full-width">
                    <c:if test="${not empty errors.from}"><span class="check-error"></span></span></c:if>
                </p>
                <p class="grid_6">
                    <label for="search2">Lotnisko docelowe</label>
                    <c:if test="${not empty errors.to}"><span class="error relative full-width"></c:if>
                    <input type="text" name="to" id="search2" value="${param.to}" class="full-width">
                    <c:if test="${not empty errors.to}"><span class="check-error"></span></span></c:if>
                </p>
            </fieldset>

            <fieldset>
                <legend>Informacje dodatkowe</legend>

                <div class="container_12">
                    <p class="grid_4">
                        <label for="datepicker">Data wylotu</label>
                        <c:if test="${not empty errors.data}"><span class="input-type-text error relative"></c:if>
                            <input type="text" name="data" id="datepicker" value="<c:out value="${param.data}"/>" class="datepicker hasDatepick"><img src="images/icons/fugue/calendar-month.png" width="16" height="16">
                        <c:if test="${not empty errors.data}"><span class="check-error"></span></span></c:if>
                    </p>
                    <p class="grid_4">
                        <label for="direct">Tylko loty bezpośrednie</label>
                        <input type="checkbox" name="direct" id="direct" value="1" class="switch" <c:if test="${param.direct == 1}">checked="checked"</c:if> style="display: none;">
                    </p>
                    <p class="grid_4">
                        <label for="klasa">Klasa</label>
                        <c:if test="${not empty errors.idKlasy}"><span class="input-type-text error relative"></c:if>
                        <select name="klasa" id="klasa" class="full-width">
                            <c:forEach var="i" items="${nazwyKlas}">
                                <option value="${i.idKlasy}" <c:if test="${param.klasa == i.idKlasy}">selected="selected"</c:if>>${i.nazwa}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty errors.idKlasy}"><span class="check-error"></span></span></c:if>
                    </p>
                </div>
                <div class="clearfix"></div>
                <div class="container_12">
                    <p class="grid_4">
                        <label for="ilosc">Ilość osób dorosłych (16+ lat)</label>
                        <c:if test="${not empty errors.ilosc}"><span class="error relative full-width"></c:if>
                        <input type="number" name="ilosc" id="ilosc" value="${param.ilosc}" class="full-width">
                        <c:if test="${not empty errors.ilosc}"><span class="check-error"></span></span></c:if>
                    </p>
                    <p class="grid_4">
                        <label for="ilosc_dz">Ilość dzieci (2-16 lat)</label>
                        <c:if test="${not empty errors.ilosc_dz}"><span class="error relative full-width"></c:if>
                        <input type="number" name="ilosc_dz" id="ilosc_dz" value="${param.ilosc_dz}" class="full-width">
                        <c:if test="${not empty errors.ilosc_dz}"><span class="check-error"></span></span></c:if>
                    </p>
                    <p class="grid_4">
                        <label for="ilosc_dz">Ilość infantów (0-2 lat)</label>
                        <c:if test="${not empty errors.ilosc_inf}"><span class="error relative full-width"></c:if>
                        <input type="number" name="ilosc_inf" id="ilosc_inf" value="${param.ilosc_inf}" class="full-width">
                        <c:if test="${not empty errors.ilosc_inf}"><span class="check-error"></span></span></c:if>
                    </p>

                </div>
                <div class="clearfix"></div>
            </fieldset>

            <fieldset class="grey-bg no-margin">
                    <button type="submit" style="float: right;">Szukaj</button>
            </fieldset>

        </form></div>
    </section>
</article>
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />