// Función principal para generar y mostrar el PDF
async function generatePDF() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);
    const referenceNumber = `REF-${Math.floor(Math.random() * 1000000)}`;

    // Crear un nuevo documento PDF
    const { PDFDocument, rgb } = PDFLib;
    const pdfDoc = await PDFDocument.create();
    const page = pdfDoc.addPage([600, 800]);
    const { width, height } = page.getSize();

    // Título del documento
    page.drawText('Comprobante de Compra', {
        x: 50,
        y: height - 50,
        size: 24,
        color: rgb(0, 0, 0.8),
    });

    // Información del cliente y referencia
    page.drawText(`Número de referencia: ${referenceNumber}`, {
        x: 50,
        y: height - 100,
        size: 14,
        color: rgb(0, 0, 0),
    });

    page.drawText('Métodos de pago disponibles:', {
        x: 50,
        y: height - 130,
        size: 14,
        color: rgb(0, 0, 0),
    });
    page.drawText('- Transferencia bancaria: Cuenta 123456789, Banco XYZ', {
        x: 50,
        y: height - 150,
        size: 12,
        color: rgb(0, 0, 0),
    });
    page.drawText('- Pago en ventanilla: Cuenta 987654321, Banco ABC', {
        x: 50,
        y: height - 170,
        size: 12,
        color: rgb(0, 0, 0),
    });

    // Tabla de productos
    let startY = height - 200;
    page.drawText('Productos:', { x: 50, y: startY, size: 16, color: rgb(0, 0, 0) });
    startY -= 20;

    cart.forEach((item) => {
        page.drawText(`${item.name} x${item.quantity} - $${item.price * item.quantity}`, {
            x: 50,
            y: startY,
            size: 12,
            color: rgb(0, 0, 0),
        });
        startY -= 20;
    });

    // Total
    page.drawText(`Total: $${total.toFixed(2)}`, {
        x: 50,
        y: startY - 20,
        size: 16,
        color: rgb(0, 0.5, 0),
    });

    // Generar el PDF en formato bytes
    const pdfBytes = await pdfDoc.save();

    // Mostrar el PDF en el iframe
    const pdfBlob = new Blob([pdfBytes], { type: 'application/pdf' });
    const pdfUrl = URL.createObjectURL(pdfBlob);
    document.getElementById('pdfViewer').src = pdfUrl;

    // Descargar el PDF al hacer clic en el botón
    document.getElementById('downloadPdf').addEventListener('click', () => {
        const link = document.createElement('a');
        link.href = pdfUrl;
        link.download = 'comprobante_compra.pdf';
        link.click();
    });
}

// Ejecutar la función al cargar la página
document.addEventListener('DOMContentLoaded', generatePDF);
