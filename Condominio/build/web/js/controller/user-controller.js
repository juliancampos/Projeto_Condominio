var defaultRoles = ['ADMIN', 'SUPPLIER', 'CLIENT', ];

function LoginCtrl($scope, $location, ProfileAuth, UserProfile) {

    $scope.actualPassword = '';
    $scope.newPassword = '';


    ProfileAuth.query({
    }, function(success) {   //success
        $scope.userProfile = success;
        $scope.userProfile.hasCredentials = function(credential) {
            return ((credential + ",").indexOf(this.role + ",") > -1);
        }
    }, function(error) {   //failure
        $scope.$emit('handleError', {
            message: error
        });
    });


    $scope.authenticateUser = function() {

        ProfileAuth.login($scope.userProfile,
                function(success) {
                    $scope.userProfile = success;
                    if ($scope.userProfile.role == 'ADMIN') {
                        $scope.isAdmin = true;
                        $location.path('/restrito/home');
                    } else if ($scope.userProfile.role == 'MANAGEMENT') {
                        $scope.isManagement = true;
                        $location.path('/restrito/home');
                    } else {
                        $location.path('/');
                    }
                }, function(error) {
            jQuery('#nopassword').show();
        })
    }

    $scope.logout = function() {

        ProfileAuth.logout(
                function(success) {
                    $scope.userProfile = {};
                    $location.path('/');
                }, function(error) {
            $location.path('/');
        })
    }
}

LoginCtrl.$inject = ['$scope', '$location', 'ProfileAuth', 'UserProfile']

