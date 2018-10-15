<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <style type="text/css">
        @page {
            size: 210mm 297mm;
            margin-left: 10mm;
            margin-right: 2mm;
            margin-top: 0mm;
            margin-bottom: 0mm;
        }
        body{
            height: 300px;
            /*font-size: 26px;*/
        }
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: left;
            width: 190mm;
        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
            font-size: 16px;
            word-break: keep-all;
            width: 20mm; 
        }
        table thead th
        {
            <#--  background-color: #CCE8EB;  -->
            width: 20mm;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
        }
    </style>
</head>
<body >
<table  class="table">
    <caption>
        <h2>
            车间能源消耗比例</h2>
    </caption>
    <thead>
    <tr>
        <th>
            车间
        </th>
        <th>
            产量
        </th>
        <th>
            电量
        </th>
        <th>
            单耗
        </th>
    </tr>
    </thead>
    <tr>
        <td>
            ${user.userName}
        </td>
        <td>
        ${user.userAge}
        </td>
        <td>
        ${user.address}
        </td>
        <td>
            213
        </td>
    </tr>
    </table>
</body>
</html>
