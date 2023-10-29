(function ($) {
    const initModalEventHandlers = () => {
        // loads content into .modal-body when the modal is being shown
        const $cityModal = $('#cityModal');
        $cityModal.on('show.bs.modal', (e) => {
            const $modal = $(e.target);
            const trigger = e.relatedTarget;
            const location = trigger.getAttribute('href');
            $modal.find('.modal-title').text(trigger.innerText);
            $modal.find('.modal-body').load(location);
        });

        // Closes the modal when the user clicks the cancel button instead of following its link
        $('.modal').on('click', '.btn-cancel', (e) => {
            e.preventDefault();
            $(e.delegateTarget).modal('hide');
        });

        // Make modals draggable
        $('.modal-dialog').draggable({
            handle: '.modal-header'
        });
    }

    initModalEventHandlers();
})(jQuery);