
### Updated JavaScript (animations.js)

```javascript
document.addEventListener('DOMContentLoaded', () => {
    anime({
        targets: '.container',
        translateY: [50, 0],
        opacity: [0, 1],
        easing: 'easeOutExpo',
        duration: 1000
    });

    document.querySelectorAll('input, textarea, select').forEach(input => {
        input.addEventListener('focus', (e) => {
            anime({
                targets: e.target,
                scale: 1.05,
                duration: 300,
                easing: 'easeOutExpo'
            });
        });

        input.addEventListener('blur', (e) => {
            anime({
                targets: e.target,
                scale: 1,
                duration: 300,
                easing: 'easeOutExpo'
            });
        });
    });

    document.querySelectorAll('button, .btn').forEach(btn => {
        btn.addEventListener('mouseenter', (e) => {
            anime({
                targets: e.target,
                scale: 1.1,
                duration: 200,
                easing: 'easeOutExpo'
            });
        });

        btn.addEventListener('mouseleave', (e) => {
            anime({
                targets: e.target,
                scale: 1,
                duration: 200,
                easing: 'easeOutExpo'
            });
        });
    });

    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const confirmed = confirm("Are you sure you want to delete this link?");
            if (!confirmed) {
                e.preventDefault();
            }
        });
    });
});
document.addEventListener('DOMContentLoaded', function () {
    const updateProfileForm = document.getElementById('updateProfileForm');
    const addLinkForm = document.getElementById('addLinkForm');
    const customizePageForm = document.getElementById('customizePageForm');

    updateProfileForm.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validateForm(updateProfileForm)) {
            this.submit();
        }
    });

    addLinkForm.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validateForm(addLinkForm)) {
            this.submit();
        }
    });

    customizePageForm.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validateForm(customizePageForm)) {
            this.submit();
        }
    });

    function validateForm(form) {
        let isValid = true;
        const inputs = form.querySelectorAll('input, textarea, select');
        inputs.forEach(input => {
            if (!input.checkValidity()) {
                isValid = false;
                input.classList.add('invalid');
            } else {
                input.classList.remove('invalid');
            }
        });
        return isValid;
    }
});
