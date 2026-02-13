// ------------------------------------------------------------
// Auto-submit du formulaire principal
// ------------------------------------------------------------

function autoSubmit(formId, selector) {
    const form = document.getElementById(formId);
    if (!form) return;

    form.querySelectorAll(selector).forEach(el => {
        el.addEventListener('change', () => {
            form.submit();
        });
    });
}

// Tous les champs du formulaire
autoSubmit('form-filter', 'input, select');


// ------------------------------------------------------------
// Désactiver les checkboxes si leur radio est désactivé
// ------------------------------------------------------------

const radioAuctions = document.getElementById('auctions');
const radioSales = document.getElementById('sales');
const auctionsFieldset = document.getElementById('auctions-checkboxes');
const salesFieldset = document.getElementById('sales-checkboxes');

function updateCheckbox() {

    // Achats
    auctionsFieldset.querySelectorAll('input[type="checkbox"]').forEach(cb => {
        cb.disabled = !radioAuctions.checked;
        if (!radioAuctions.checked) cb.checked = false;
    });

    // Ventes
    salesFieldset.querySelectorAll('input[type="checkbox"]').forEach(cb => {
        cb.disabled = !radioSales.checked;
        if (!radioSales.checked) cb.checked = false;
    });
}

radioAuctions.addEventListener('change', updateCheckbox);
radioSales.addEventListener('change', updateCheckbox);

// Initialisation au chargement
updateCheckbox();




