import { RouterModule, Routes } from '@angular/router';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { ListClientComponent } from './list-client/list-client.component';

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
       path: '',
       component: ListClientComponent
    },
];

export const routing = RouterModule.forRoot(routes);
