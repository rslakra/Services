angular.module('app.controllers', []).controller('ShipWreckListController',
    function ($scope, $state, popupService, $window, ShipWreck) {
        $scope.shipwrecks = ShipWreck.query();
        /*
         * fetch all shipwrecks. Issues a GET to /api/vi/shipwrecks
         */
        $scope.deleteShipWreck = function (shipWreck) {
            /*
             * Delete a ShipWreck. Issues a DELETE to /api/v1/shipwrecks/:id
             */
            if (popupService.showPopup('Really delete this?')) {
                shipWreck.$delete(function () {
                    $scope.shipwrecks = ShipWreck.query();
                    $state.go('shipWrecks');
                });
            }
        };
    }).controller('ShipWreckViewController',
    function ($scope, $stateParams, ShipWreck) {
        // Get a single shipwreck.Issues a GET to /api/v1/shipwrecks/:id
        $scope.shipwreck = ShipWreck.get({
            id: $stateParams.id
        });
    }).controller('ShipWreckCreateController',
    function ($scope, $state, $stateParams, ShipWreck) {
        $scope.shipwreck = new ShipWreck();
        /*
         * create new shipwreck instance. Properties will be set via
         * ng-model on UI
         */
        $scope.addShipWreck = function () {
            /**
             * Create a new shipwreck
             * POST /api/v1/shipwrecks
             */
            $scope.shipwreck.$save(function () {
                console.log("shipwreck");
                /*
                 * on success go back to the list i.e. shipwrecks state.
                 */
                $state.go('shipWrecks');
            });
        };
    }).controller('ShipWreckEditController',
    function ($scope, $state, $stateParams, ShipWreck) {
        $scope.updateShipWreck = function () {
            /*
             * Update the edited shipwreck. Issues a PUT to
             * /api/v1/shipwrecks/:id
             */
            $scope.shipwreck.$update(function () {
                /*
                 * on success go back to the list i.e. shipwrecks state.
                 */
                $state.go('shipWrecks');
            });
        };

        $scope.loadShipWreck = function () {
            /*
             * Issues a GET request to /api/v1/shipwrecks/:id to get a
             * shipwreck to update
             */
            $scope.shipwreck = ShipWreck.get({
                id: $stateParams.id
            });
        };

        // Load a shipwreck which can be edited on UI
        $scope.loadShipWreck();
    });
