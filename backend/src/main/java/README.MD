yo lo que hago es usar esa notación: @CurrentSecurityContext(expression="authentication?.name") String username

@PreAuthorize("hasAuthority('USER')")
@DeleteMapping("/{id}")
@ApiOperation("Deletes user")
public ResponseEntity<?> deleteUser(@PathVariable Long id, @CurrentSecurityContext(expression="authentication?.name") String username){
return userService.deleteUser(id, username);
}

Que quieres que el usuario solo se pueda borrar a el mismo?
@CurrentSecurityContext(expression="authentication?.name") String username

@PreAuthorize("hasAuthority('USER')")
@DeleteMapping("/)
@ApiOperation("Deletes user")
public ResponseEntity<?> deleteUser( @CurrentSecurityContext(expression="authentication?.name") String username){
return userService.deleteUserByUsername(username);
}
no necesitas pedir el id

s que el mismo servicio puede ser utilizado por el admin, por eso hace falta id
//Tests if the user is allowed to edit this post (only authors and admins can do it)
//If the user isn't the one trying to update, checks to see if the user is ADMIN
if (!userOpt.get().getUsername().equalsIgnoreCase(username)){

            boolean isAdmin = false;

            for (Role role  : userOpt.get().getRoles()){
                if (role.getName().equalsIgnoreCase("ADMIN")) isAdmin = true;
            }

            if (!isAdmin)
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("The user " + username + " is not allowed to update the post " ));
        }

        userRepository.delete(userOpt.get());