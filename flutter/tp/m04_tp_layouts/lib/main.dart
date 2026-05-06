import 'package:flutter/material.dart';
import 'package:m04_tp_layouts/entities/Project.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    final colorScheme = ColorScheme.fromSeed(seedColor: Colors.indigo);

    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: colorScheme,
        scaffoldBackgroundColor: const Color(0xfff3f3f3),
        appBarTheme: AppBarTheme(backgroundColor: colorScheme.inversePrimary),
        textTheme: TextTheme(
          titleLarge: TextStyle(
            fontSize: 24,
            color: colorScheme.primary,
            fontWeight: FontWeight(700),
          ),
        ),
        bottomNavigationBarTheme: BottomNavigationBarThemeData(
          backgroundColor: Colors.black,
          unselectedItemColor: Colors.grey,
          selectedItemColor: Colors.blue,
        ),
      ),
      home: MyHomePage(title: 'TP Mes projets'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<StatefulWidget> createState() {
    return _MyHomePageState();
  }
}

class _MyHomePageState extends State<MyHomePage> {
  int currentPageIndex = 0;
  Widget _homePage = HomePage();
  Widget _contributionPage = ContributionPage();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // ***************
      // *** APP BAR ***
      // ***************
      appBar: AppBar(
        leading: Icon(Icons.rocket_launch, color: Colors.white),
        elevation: 8,
        shadowColor: Colors.black,
        backgroundColor: Colors.indigo,
        centerTitle: true,
        foregroundColor: Colors.white,
        title: Text(widget.title),
      ),

      // ***************
      // *** NAVIGATION BAR ***
      // ***************
      bottomNavigationBar: NavigationBar(
        indicatorColor: Colors.blueAccent,
        onDestinationSelected: (int index) {
          setState(() {
            currentPageIndex = index;
          });
        },
        selectedIndex: currentPageIndex,
        destinations: const <Widget>[
          NavigationDestination(
            icon: Icon(Icons.folder_open),
            label: 'Projets',
          ),
          NavigationDestination(
            icon: Icon(Icons.add_circle_outline),
            label: 'Contribuer',
          ),
        ],
      ),
      // ***************
      // *** BODY ***
      // ***************
      body: this.getBody(),
    );
  }

  Widget getBody() {
    List<Widget> pages = [_homePage, _contributionPage];

    return pages[currentPageIndex];
  }
}

class HomePage extends StatelessWidget {
  HomePage({super.key});

  final List<Project> projects = [
    Project("Projet 1", "Spring boot"),
    Project("Projet 2", "Symfony + Twig"),
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      child: Column(
        children: [
          Container(
            padding: const EdgeInsets.only(bottom: 16),
            child: Text(
              'Liste de projets',
              style: Theme.of(context).textTheme.titleLarge,
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: projects.length,
              itemBuilder: (BuildContext context, int index) {
                return Card(
                  color: Colors.black,
                  elevation: 4,
                  child: ListTile(
                    leading: Icon(Icons.folder),
                    title: Text(
                      projects[index].title,
                      style: TextStyle(color: Colors.white),
                    ),
                    subtitle: Text(
                      projects[index].description,
                      style: TextStyle(color: Colors.grey),
                    ),
                    trailing: Icon(Icons.chevron_right),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

class ContributionPage extends StatelessWidget {
  ContributionPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Text('Page de contribution');
  }
}
