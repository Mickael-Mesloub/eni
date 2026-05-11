import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:m04_tp_layouts/entities/Project.dart';
import 'package:m04_tp_layouts/main.dart';

class ProjectDetailsPage extends StatelessWidget {
  final Project project;

  const ProjectDetailsPage({required this.project});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actionsPadding: EdgeInsets.only(right: 20),
        leading: BackButton(),
        actions: [
          IconButton(icon: Icon(Icons.edit), onPressed: () {
            context.push('/details/edit', extra: ScreenArguments(project));
          },)
        ],
        title: Text(project.name),
        centerTitle: true,
        elevation: 2,
        shadowColor: Colors.black,
      ),
      body: Column(
        children: [
          Text(project.name),
          Text(project.desc),
          Text(project.status.name),

        ],
      )
    );
  }

}