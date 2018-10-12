<html>
<head>
    <title></title>
    <style type="text/css">
        body{
            height: 300px;
            /*font-size: 26px;*/
        }
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
            font-size: 20px;
        }
        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
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
<table width="90%" class="table">
    <caption>
        <h2>
            ������Դ���ı���</h2>
    </caption>
    <thead>
    <tr>
        <th>
            ����
        </th>
        <th>
            ����
        </th>
        <th>
            ����
        </th>
        <th>
            ����
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
