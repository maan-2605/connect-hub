document.addEventListener('DOMContentLoaded', () => {
    anime({
        targets: '.container',
        translateY: [50, 0],
        opacity: [0, 1],
        easing: 'easeOutExpo',
        duration: 1000
    });

    document.querySelectorAll('input').forEach(input => {
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

    document.querySelectorAll('.btn').forEach(btn => {
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
});
