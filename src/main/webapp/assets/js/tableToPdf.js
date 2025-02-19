window.onload = () => {
    const numberElements = document.querySelectorAll('.number');

    numberElements.forEach(el => {
        const originalValue = parseFloat(el.textContent);
        if (!isNaN(originalValue)) {
            const formattedValue = new Intl.NumberFormat('fr-FR', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
            }).format(originalValue);
            el.textContent = formattedValue;
        }
    });
}

function generateTable() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF({ orientation: "landscape" });

    const numberElements = document.querySelectorAll('.number');
    numberElements.forEach(el => {
        const originalValue = el.textContent;
        const formattedValue = originalValue.replace(/[^0-9,.-]+/g, '').replace(/,/g, '.')
        el.textContent = formattedValue;
    });

    doc.setFontSize(18);
    doc.text("Mr meuble:", 14, 15);
    doc.setFontSize(12);
    doc.text("Généré le: " + new Date().toLocaleDateString(), 14, 25);
    doc.line(10, 30, 280, 30);
    doc.setFontSize(8);

    doc.text("Tableau des charges", 14, 40);
    const headers = [
        [{ content: 'Rubrique', rowSpan: 2 }, { content: 'Total', rowSpan: 2 }, { content: "UO", rowSpan: 2 }, { content: 'N', rowSpan: 2 }, { content: 'ADM/DIST', colSpan: 3 }, { content: 'USINE', colSpan: 3 }, { content: 'ATELIER', colSpan: 3 }, { content: 'TOTAL', colSpan: 3 }],
        ['%', 'Fixe', 'Variable', '%', 'Fixe', 'Variable', '%', 'Fixe', 'Variable', '', 'Fixe', 'Variable']
    ];

    const tableData = [];
    const rows = document.querySelectorAll("#table tbody tr");
    rows.forEach(row => {
        const rowData = [];
        row.querySelectorAll("td").forEach(cell => {
            rowData.push(cell.textContent.trim());
        });
        tableData.push(rowData);
    });

    const footerData = [];
    const footerRows = document.querySelectorAll("#table tfoot tr");
    footerRows.forEach(row => {
        const rowData = [];
        const cells = row.querySelectorAll("th, td");
        cells.forEach(cell => {
            const colspan = cell.getAttribute('colspan') || 1;
            console.log(colspan)
            const rowspan = cell.getAttribute('rowspan') || 1;
            const cellContent = cell.textContent.trim();

            for (let i = 0; i < colspan; i++) {
                rowData.push(cellContent);
            }
        });
        footerData.push(rowData);
    });

    const allTableData = [...tableData, ...footerData];

    doc.autoTable({
        head: headers,
        body: allTableData,
        startY: 50,
        theme: 'plain',
        headStyles: { fillColor: [211, 211, 211], fontSize: 7, lineWidth: 0.1, lineColor: [0, 0, 0] },
        bodyStyles: { fontSize: 6, lineWidth: 0.1, lineColor: [0, 0, 0] },
        footStyles: { fillColor: [211, 211, 211], fontSize: 7, fontStyle: 'bold' },
        didParseCell: function (data) {
            var rows = data.table.body;
            if (data.row.index === rows.length - 1) {
                data.cell.styles.fontStyle = 'bold';
            }
            if (data.row.index === rows.length - 2) {
                data.cell.styles.fontStyle = 'bold';
            }
        },
        margin: { top: 30 },
        styles: {
            cellPadding: 2,
        },
        columnStyles: {
            0: { cellWidth: 25 },
            1: { cellWidth: 20 },
            2: { cellWidth: 8 },
            3: { cellWidth: 8 },
        },
    });

    doc.addPage()
    doc.text("Tableau de répartition", 14, 15);

    const headersTable2 = [['REPARTITION ADM/DIST', 'Cout direct', '', 'Clés (%)', 'ADM/DIST', 'Cout total']];
    const table2Data = [];
    const rowsTable2 = document.querySelectorAll("#table-2 tbody tr");
    rowsTable2.forEach(row => {
        const rowData = [];
        row.querySelectorAll("td").forEach(cell => {
            rowData.push(cell.textContent.trim());
        });
        table2Data.push(rowData);
    });

    const footerTable2 = [];
    const footerRowsTable2 = document.querySelectorAll("#table-2 tfoot tr");
    footerRowsTable2.forEach(row => {
        const rowData = [];
        row.querySelectorAll("th, td").forEach(cell => {
            rowData.push(cell.textContent.trim());
        });
        footerTable2.push(rowData);
    });

    const allTable2Data = [...table2Data, ...footerTable2];

    doc.autoTable({
        head: headersTable2,
        body: allTable2Data,
        startY: 25,
        theme: 'plain',
        headStyles: { fillColor: [211, 211, 211], fontSize: 7 },
        bodyStyles: { fontSize: 6 },
        footStyles: { fillColor: [211, 211, 211], fontSize: 7, fontStyle: 'bold' },
        didParseCell: function (data) {
            var rows = data.table.body;
            if (data.row.index === rows.length - 1) {
                data.cell.styles.fontStyle = 'bold';
            }
        },
        margin: { top: 20 },
        styles: { cellPadding: 2 },
    });

    doc.addPage();
    doc.text("Calcul de coût de revient", 14, 15);

    const headersTable3 = [['COUT DU KILOS DE MAIS GRAIN', '']];
    const table3Data = [];
    const rowsTable3 = document.querySelectorAll("#table-3 tbody tr");
    rowsTable3.forEach(row => {
        const rowData = [];
        row.querySelectorAll("td").forEach(cell => {
            rowData.push(cell.textContent.trim());
        });
        table3Data.push(rowData);
    });

    const footerTable3 = [];
    const footerRowsTable3 = document.querySelectorAll("#table-3 tfoot tr");
    footerRowsTable3.forEach(row => {
        const rowData = [];
        row.querySelectorAll("th, td").forEach(cell => {
            rowData.push(cell.textContent.trim());
        });
        footerTable3.push(rowData);
    });

    const allTable3Data = [...table3Data, ...footerTable3];

    doc.autoTable({
        head: headersTable3,
        body: allTable3Data,
        startY: 25,
        theme: 'plain',
        headStyles: { fillColor: [211, 211, 211], fontSize: 7 },
        bodyStyles: { fontSize: 6 },
        footStyles: { fillColor: [211, 211, 211], fontSize: 7, fontStyle: 'bold' },
        didParseCell: function (data) {
            var rows = data.table.body;
            if (data.row.index === rows.length - 1) {
                data.cell.styles.fontStyle = 'bold';
            }
        },
        margin: { top: 20 },
        styles: { cellPadding: 2 },
    });

    numberElements.forEach(el => {
        const originalValue = parseFloat(el.textContent);
        if (!isNaN(originalValue)) {
            const formattedValue = new Intl.NumberFormat('fr-FR', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
            }).format(originalValue);
            el.textContent = formattedValue;
        }
    });

    doc.save(`tables-${new Date().toLocaleDateString()}-${new Date().toLocaleTimeString()}.pdf`);
}

const btn = document.getElementById("generate");
btn.addEventListener('click', generateTable);
