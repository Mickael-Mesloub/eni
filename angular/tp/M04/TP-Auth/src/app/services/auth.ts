import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  // Attributs
  isAuth!: boolean

  // Fonction login
    // Récupérer un User (avec username et password)
    // Vérifier si je le trouve en localStorage
    // Vérifier si les informations correspondent
    // Si oui => isAuth passe à true + redirection vers /profile
    // Si non => afficher un message d'erreur (redirection ?)

  // Fonction register
    // Je dois créer un array d'users
    //  utilisateur en localStorage avec :
      // username: Minimum 2 caractères
      // email
      // password: Minimum 6 caractères
      // PENSER A TRIM
}
