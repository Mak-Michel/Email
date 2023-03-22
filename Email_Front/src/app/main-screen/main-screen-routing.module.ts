import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainScreenComponent } from './main-screen.component';
import { InboxComponent } from './m-components/inbox/inbox.component';
import { TrashComponent } from './m-components/trash/trash.component';
import { SentComponent } from './m-components/sent/sent.component';
import { DraftComponent } from './m-components/draft/draft.component';
import { ComposeComponent } from './m-components/compose/compose.component';
import { AddContactComponent } from './m-components/contacts/add-contact/add-contact.component';
import { ContactsComponent } from './m-components/contacts/contacts.component';
import { ShowFoldersComponent } from './m-components/show-folders/show-folders.component';
import { CreateFolderComponent } from './m-components/create-folder/create-folder.component';
import { ContactDisplayComponent } from './m-components/contacts/contact-display/contact-display.component';
import { FoldersComponent } from './m-components/show-folders/folders/folders.component';
import { MessaegeComponent } from './m-components/messaege/messaege.component';
const routes: Routes = [
  {
    path: 'main-screen',
    component: MainScreenComponent,
    children: [
      { path: '', redirectTo: '/main-screen/inbox', pathMatch: 'full' }, // if path: '' redirect to inbox
      { path: 'inbox', component: InboxComponent },
      { path: 'inbox/:function/:functionParameter', component: InboxComponent },
      { path: 'trash', component: TrashComponent },
      { path: 'sent', component: SentComponent },
      { path: 'draft', component: DraftComponent },
      { path: 'compose/:reciever/:emailId', component: ComposeComponent },
      { path: 'email/:emailId', component: MessaegeComponent },
      { path: 'compose', component: ComposeComponent },      
      { path: 'add-contact', component: AddContactComponent },
      { path: 'add-contact/:contactName', component: AddContactComponent },
      { path: 'contacts', component: ContactsComponent },
      { path: 'contact-display/:contactName', component: ContactDisplayComponent },
      { path: 'create-folder', component: CreateFolderComponent },
      { path: 'folder/:folder-name', component: FoldersComponent },
    ],
  },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainScreenRoutingModule {}
