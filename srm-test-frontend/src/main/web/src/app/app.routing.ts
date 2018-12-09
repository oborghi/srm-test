import { Routes, RouterModule } from '@angular/router';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { ListClientComponent } from './list-client/list-client.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    {
        path: 'list-client',
        component: ListClientComponent
    },
    {
        path: 'add-client',
        component: AddClientComponent
    },
    {
       path: 'edit-client',
       component: EditClientComponent
    },
    {
       path: 'login',
       component: LoginComponent
    },
    {
       path: '**',
       redirectTo: 'login'
    }
];

export const routing = RouterModule.forRoot(routes);
