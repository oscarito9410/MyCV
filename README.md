<p><strong>My CV Resume App</strong></p>
<p><em><strong>MVP+ CLEAN ARCHITECTURE+ KOIN + KOTLIN =&nbsp;&hearts;</strong></em></p>
<p>This application makes use of MVP as pattern for presentation layer. Uses clean architecture for separation of code in different layers with assigned responsibilities in order to make it easier for further modification.</p>
<p><em>The application is divided in the following layers</em></p>
<ul>
<li><strong>Presentation (App module) :</strong> Would include both domain and data layer and is android specific which executes the UI logic.</li>
<li><strong>Data:</strong> Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain</li>
<li><strong>Domain:</strong> Would execute business logic which is independent of any layer and is just a pure kotlin package with no android specific dependency.</li>
<li><strong>Use case:</strong> The application logic executor. As the name depicts each functionality can have its separate use case.</li>
</ul>
<p>In addition in this application i make use of koin di framework which works very well with klotin</p>
