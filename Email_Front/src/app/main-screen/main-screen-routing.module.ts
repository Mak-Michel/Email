import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainScreenComponent } from './main-screen.component';
import { InboxComponent } from './m-components/inbox/inbox.component';
import { TrashComponent } from './m-components/trash/trash.component';
import { SentComponent } from './m-components/sent/sent.component';
import { DraftComponent } from './m-components/draft/draft.component';
import { ComposeComponent } from './m-components/compose/compose.component';
import { AddContactComponent } from './m-components/add-contact/add-contact.component';
import { ContactsComponent } from './m-components/contacts/contacts.component';
import { ShowFoldersComponent } from './m-components/show-folders/show-folders.component';
import { CreateFolderComponent } from './m-components/create-folder/create-folder.component';
const routes: Routes = [
  {
    path: 'main-screen',
    component: MainScreenComponent,
    children: [
      { path: '', redirectTo: '/main-screen/inbox', pathMatch: 'full' }, // if path: '' redirect to inbox
      { path: 'inbox', component: InboxComponent },
      { path: 'trash', component: TrashComponent },
      { path: 'sent', component: SentComponent },
      { path: 'draft', component: DraftComponent },
      { path: 'compose', component: ComposeComponent },
      { path: 'add-contact', component: AddContactComponent },
      { path: 'contacts', component: ContactsComponent },
      { path: 'create-folder', component: CreateFolderComponent },
      { path: 'show-folders', component: ShowFoldersComponent },
    ],
  },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainScreenRoutingModule {}
