class Project {
  String _title;
  String _description;

  Project(this._title, this._description);

  String get description => _description;

  set description(String value) {
    _description = value;
  }

  String get title => _title;

  set title(String value) {
    _title = value;
  }
}