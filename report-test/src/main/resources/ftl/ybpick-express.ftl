<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
          @page {
          	 size: 210mm 297mm;
          	 margin-left: 10mm;
          	 margin-right: 2mm;
          	 margin-top: 0mm;
          	 margin-bottom: 0mm;
          }
       body {
                font-family:'宋体';
                font-size: 12px;
                color: #000000;
                border-color: #000;
                table-layout: fixed;
                margin-top: 0mm;
                margin-bottom: 0mm;
                width:100%;
              }
          h1 {
          	text-align: center;
          }
         .table-list {
         	width: 100%;
         	border: black 1px solid;
            border-collapse: collapse;
            margin-top: 0mm;
            margin-bottom: 0mm;
         }
        .box , .page_next {
            page-break-after: always;
        }
        td ,th{
            <#--  font-family:'宋体';  -->
            font-size: 12px;
            border: black 1px solid;
            height: 20px;
            line-height: 20px;
            padding-left: 1mm;
            padding-right: 1mm;
        }
        .caption {
            <#--  font-family:'宋体';  -->
            font-size: 40px;
            font-weight: bold;
            text-align:  left;
            border: solid 0px black !important;
            height: 18mm;
            margin-top: 7mm;
            line-height: 18mm;
            position: relative;
        }
    </style>
</head>

<body>
 <div class="caption row">
        交付指示单
    </div>

    <table class="table-list" style="font-weight:bold;">
        <tr>
            <td style="text-align: left;"  ><b>收货道口:</b></td>
            <td style="text-align: left;" ><b>1231231</b></td>
            <td style="text-align: left" colspan=2 ><b>需求车间：1231231</b> <br/>
                <b>需求产线：1231231</b>
            </td>
        </tr>
        <tr>
            <td style="text-align: left;"  ><b><#if sos=true>
            &diams;
            <#else>
            &loz; </#if> 紧急</b></td>
            <td style="text-align: left;" ><b><#if (sos=true)>
            &loz;
            <#else>
            &diams;
             </#if>正常</b></td>
            <td style="text-align: left"  colspan=2 ><b>单号：1231231</b>
            </td>
        </tr>
                
    </table>
 <#assign s=1>
        <#list items as item>
        <#assign s1=1>
<div class="box" style="page-break-after: always;">
    
    <table class="table-list ">
    
     <thead>
                 <tr>
                    <td align="center" >序号</td>
                    <td align="center" >零件件号</td>
                    <td align="center" >零件名称</td>
                    <td align="center" >需求箱数</td>
                </tr>
     </thead>
               <#list item as sitem>
                <tr >
                    <td align="center">${s*s1}</td>
                    <td align="center">101</td>
                    <td align="center">${sitem.userBean.userName}</td>
                    <td align="center">1</td>
                </tr>
                <#assign s1 +=1 >
                </#list>           
    </table>
    
</div>
 <#assign s +=1 >
                </#list>
</body>
</html>