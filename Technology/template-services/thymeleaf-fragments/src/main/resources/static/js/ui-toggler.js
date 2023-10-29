(function ($, window) {
    const UI_MODES = {
        ENRICHED: 'enriched',
        PLAIN: 'plain'
    };
    let uiMode = UI_MODES.ENRICHED;
    const uiModeStorageKey = 'uiMode';

    function initInlineEditable() {
        $('.inline-editable').editable({mode: 'inline'});
    }

    function uiStandardHtml() {
        $('.inline-editable').editable('destroy');
        $('.city-link')
            .removeAttr('role')
            .removeAttr('data-toggle')
            .removeAttr('data-target')
            .removeAttr('data-remote')
            .removeClass('btn btn-default');
    }

    function uiEnriched() {
        initInlineEditable();

        // change all city-link links into modal triggers, but don't let Bootstrap do its magic loading the content into the
        // modal-content div because it messes up the UI.
        $('.city-link')
            .attr('role', 'button')
            .attr('data-toggle', 'modal')
            .attr('data-target', '#cityModal')
            .attr('data-remote', 'false')
            .addClass('btn btn-default');
    }

    function toggleUi() {
        const _uiMode = getUiMode();
        if (_uiMode === UI_MODES.ENRICHED) {
            uiStandardHtml();
            setUiMode(UI_MODES.PLAIN)
        } else {
            uiEnriched();
            setUiMode(UI_MODES.ENRICHED);
        }
    }

    function addButtonToToggleUi() {
        const $toggleUiBtn = $('<a></a>')
            .attr('role', 'button')
            .text('Toggle UI')
            .on('click', toggleUi);
        $('#ui-toggle')
            .html($toggleUiBtn)
            .removeClass('hidden');
    }

    function supportsSessionStorage() {
        if (window.sessionStorage === undefined) {
            return false;
        }

        try {
            window.sessionStorage.setItem('test', '');
            window.sessionStorage.removeItem('test');
            return true;
        } catch (e) {
            return false;
        }
    }

    function getUiMode() {
        if (supportsSessionStorage()) {
            const storedMode = window.sessionStorage.getItem(uiModeStorageKey) || '';
            if (storedMode.length > 0) {
                uiMode = storedMode;
            }
        }
        return uiMode;
    }

    function setUiMode(_uiMode) {
        if (supportsSessionStorage()) {
            window.sessionStorage.setItem(uiModeStorageKey, _uiMode);
        } else {
            uiMode = _uiMode;
        }
    }

    function initUi() {
        const preferredUiMode = getUiMode();
        if (preferredUiMode === UI_MODES.ENRICHED) {
            uiEnriched();
        }
        addButtonToToggleUi();
    }

    initUi();
})(jQuery, window);