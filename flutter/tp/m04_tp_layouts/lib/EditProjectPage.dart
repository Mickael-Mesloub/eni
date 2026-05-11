import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:m04_tp_layouts/ContributionPage.dart';
import 'package:m04_tp_layouts/entities/Project.dart';

class EditProjectPage extends StatefulWidget {
  final Project project;

  const EditProjectPage({required this.project});

  @override
  State<EditProjectPage> createState() => _EditProjectPageState();
}

class _EditProjectPageState extends State<EditProjectPage> {
  void _onSubmit(Project project) {
    setState(() {
      print(project.toString());
      context.pop();
    });

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text('Projet "${project.name}" modifié !'),
        backgroundColor: Colors.green,),
    );
  }

  // TODO: Remplir les champs avec les infos du projet
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actionsPadding: EdgeInsets.only(right: 20),
        leading: BackButton(),
        title: Text("Modifier le projet"),
        centerTitle: true,
        elevation: 2,
        shadowColor: Colors.black,
      ),
      body: ContributionPage(onProjectSubmitted: _onSubmit, project: widget.project,),
    );
  }
}



