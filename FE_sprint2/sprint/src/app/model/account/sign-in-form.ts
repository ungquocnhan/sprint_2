export class SignInForm {
  username: string;
  encryptPassword: string;

  constructor(username: string, encryptPassword: string) {
  this.username = username;
  this.encryptPassword = encryptPassword;
}
}
