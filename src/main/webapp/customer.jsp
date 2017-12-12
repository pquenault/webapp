<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Votre compte</title>
        <style>
            h1 {
                font-size : 64px;
                color : tomato;
                text-align : center;
            }
            h2 {
                color : orange;
                background-color: #f2f2f2;
                text-align : center;
                box-shadow : 2px 2px 2px grey;
            }
            h3 {
                color : blue;
            }
            table {
                box-shadow : 2px 2px 2px grey;
            }
        </style>
    </head>
    <body>
        <h1>Votre compte</h1>

        <div>
            <h2>Vos informations</h2>
            <table border="1">
                <tr>
                    <th>Nom</th>
                    <th>Adresse n°1</th>
                    <th>Adresse n°2</th>
                    <th>Code postal</th>
                    <th>Ville</th>
                    <th>État</th>
                    <th>Téléphone</th>
                    <th>Fax</th>
                    <th>Email</th>
                    <th>Limite de crédit</th>
                </tr>
                <tr>
                    <td>${customer.name}</td>
                    <td>${customer.adressLine1}</td>
                    <td>${customer.adressLine2}</td>
                    <td>${customer.zip}</td>
                    <td>${customer.city}</td>
                    <td>${customer.state}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.fax}</td>
                    <td>${customer.email}</td>
                    <td>${customer.creditLimit}</td>
                </tr>
            </table>
        </div>

        <div>
            <h2>Vos commandes</h2>
            <table border="1">
                <tr>
                    <th>N°</th>
                    <th>Produit</th>
                    <th>Prix à l'unité</th>
                    <th>Quantité</th>
                    <th>Prix du lot</th>
                    <th>Frais de port</th>
                    <th>Total</th>
                    <th>Effectuée le</th>
                    <th>Expédiée le</th>
                    <th>Compagnie de fret</th>
                </tr>
                <c:forEach var="purchaseOrder" items="${purchaseOrders}">
                    <c:forEach var="product" items="${products}">
                        <c:if test="${purchaseOrder.productId == product.productId}">
                            <c:set var="actualProduct" value="${product}"/>
                            <c:set var="price" value="${product.purchaseCost}"/>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <td>${purchaseOrder.orderNum}</td>
                        <td>${actualProduct.description}</td>
                        <td>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value="${price}"
                                              type="currency"
                                              minFractionDigits="0" maxFractionDigits="2"
                                              minIntegerDigits="0" />
                        </td>
                        <td>${purchaseOrder.quantity}</td>
                        <td>
                            <fmt:formatNumber value="${price * purchaseOrder.quantity}"
                                              type="currency"
                                              minFractionDigits="0" maxFractionDigits="2"
                                              minIntegerDigits="0" />
                        </td>
                        <td>
                            <fmt:formatNumber value="${purchaseOrder.shippingCost / 100}"
                                              type="percent"
                                              minFractionDigits="0" maxFractionDigits="2"
                                              minIntegerDigits="0" maxIntegerDigits="2"/>
                        </td>
                        <td></td>
                        <td>${purchaseOrder.salesDate}</td>
                        <td>${purchaseOrder.shippingDate}</td>
                        <td>${purchaseOrder.freightCompany}</td>
                        <td>
                            <a href="?action=DELETE&orderNum=${purchaseOrder.orderNum}&quantity=${purchaseOrder.quantity}&productId=${purchaseOrder.productId}">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <%--  On montre un éventuel message d'erreur --%>
        <div><p>${message}</p></div>

        <div>
            <h2>Catalogue</h2>
            <c:forEach var="productCode" items="${productCodes}">
                <c:forEach var="discountCode" items="${discountCodes}">
                    <c:if test="${productCode.discountCodeId == discountCode.discountCodeId}">
                        <c:set var="rate" value="${discountCode.rate}"/>
                    </c:if>
                </c:forEach>
                <h3>${productCode.description}</h3>
                <table border="1">
                    <tr>
                        <th>Description</th>
                        <th>Fabricant</th>
                        <th>Prix à l'unité</th>
                        <th>Remise</th>
                        <th>Prix remisé</th>
                        <th>Disponible</th>
                        <th>Quantité disponible</th>
                    </tr>
                    <c:forEach var="product" items="${products}">
                        <c:if test="${productCode.productCodeId == product.productCodeId}">
                            <c:set var="price" value="${product.purchaseCost}"/>
                            <tr>
                                <td>${product.description}</td>
                                <td></td>
                                <td>
                                    <fmt:formatNumber value="${price}"
                                                      type="currency"
                                                      minFractionDigits="0" maxFractionDigits="2"
                                                      minIntegerDigits="0" />
                                </td>
                                <td>
                                    <fmt:formatNumber value="${rate / 100}"
                                                      type="percent"
                                                      minFractionDigits="0" maxFractionDigits="2"
                                                      minIntegerDigits="0" maxIntegerDigits="2"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${price - (price * (rate/100))}"
                                                      type="currency"
                                                      minFractionDigits="0" maxFractionDigits="2"
                                                      minIntegerDigits="0" />
                                </td>
                                <c:if test="${product.available == 'TRUE'}">
                                    <td style="background-color: green">Oui</td>
                                </c:if>
                                <c:if test="${product.available == 'FALSE'}">
                                    <td style="background-color: red">Non</td>
                                </c:if>
                                <td>${product.quantityOnHand}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:forEach>
        </div>
    </body>
</html>
