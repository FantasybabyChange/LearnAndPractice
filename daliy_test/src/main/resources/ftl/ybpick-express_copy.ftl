<!DOCTYPE html>
<html>
<head>
        <style type="text/css">
            body {
                font-family:'宋体';
                font-size: 12px;
                color: #000000;
                border-color: #000;
                table-layout: fixed;
                margin-top: 0mm;
                margin-bottom: 0mm;
            }
             

            @page {
                size: 210mm 297mm;
                margin-left: 10mm;
                margin-right: 10mm;
                margin-top: 0mm;
                margin-bottom: 0mm;
            }

            .box{
                width: 195mm;
                height: 297mm;
                border: solid 0px black !important;
            }

            .table-list {
                margin-left: 7px;
                margin-right: 6px;
                border-collapse: collapse;
                table-layout: fixed;
                background-color: #FFFFFF;
                border: solid 1px black !important;
            }

            .table-list p {
                line-height: 14px !important;
                margin: 0px 0px;
            }

            .table-list td, .table-list th {
                font-family:'宋体';
                font-size: 12px;
                border: solid 1px black !important;
                height: 20px;
                line-height: 20px;
                padding-left: 1mm;
                padding-right: 1mm;
            }
            .caption {
                font-family:'宋体';
                font-size: 40px;
                font-weight: bold;
                text-align:  left;
                border: solid 0px black !important;
                height: 18mm;
                margin-top: 7mm;
                line-height: 18mm;
                position: relative;
            }
            .barcode {
                width: 66mm ;
                height: 17mm;
                float: right;
            }
            .box , .page_next {
                page-break-after: always;
            }
    </style>
</head>

<body>
<div class="box">
    <div class="caption row">
        交付指示单
    </div>

    <table class="table-list" style="font-weight:bold;">
        <tr>
            <td style="text-align: left;width:12%;"  ><b>收货道口:</b></td>
            <td style="text-align: left;width:12%;" ><b>1231231</b></td>
            <td style="text-align: left" ><b>需求车间：1231231</b> <br/>
                <b>需求产线：1231231</b>
            </td>
        </tr>
        <tr>
        
            <td style="text-align: left;width:9%;"  ><b><#if sos=true>
            &diams;
            <#else>
            &loz; </#if> 紧急</b></td>
            <td style="text-align: left;width:9%;"" ><b><#if (sos=true)>
            &loz;
            <#else>
            &diams;
             </#if>正常</b></td>
            <td style="text-align: left" ><b>单号：1231231</b>
            </td>
        </tr>
    </table>

    <table class="table-list">
        <thead>
        <tr>
            <th style="width:1%;">序号</th>
            <th style="width:10%;">零件件号</th>
            <th style="width:10%;">零件名称</th>
            <th style="width:2%;">需求箱数</th>

        </tr>
        </thead>
        <tbody>
        <#assign s=0>
        <#list items as item>
        <tr >
            <td align="center">${startIndex+s}</td>
            <td align="center">101</td>
            <td align="center">${item.userBean.userName}</td>
            <td align="center">1</td>
        </tr>
        <#assign s +=1 >
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>