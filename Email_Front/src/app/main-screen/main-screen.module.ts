import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InboxComponent } from './m-components/inbox/inbox.component';
import { TrashComponent } from './m-components/trash/trash.component';
import { DraftComponent } from './m-components/draft/draft.component';
import { ComposeComponent } from './m-components/compose/compose.component';
import { ContactsComponent } from './m-components/contacts/contacts.component';
import { SentComponent } from './m-components/sent/sent.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MainScreenRoutingModule } from './main-screen-routing.module';
import { MainScreenComponent } from './main-screen.component';
import { EmailButtonComponent } from './m-components/button/email-button.component';
import { ShowFoldersComponent } from './m-components/show-folders/show-folders.component';
import { FoldersComponent } from './m-components/show-folders/folders/folders.component';
import { CreateFolderComponent } from './m-components/create-folder/create-folder.component';
import { Button2Component } from './m-components/show-folders/folders/button2/button2.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AddContactComponent } from './m-components/contacts/add-contact/add-contact.component';
import { Button3Component } from './m-components/contacts/button3/button3.component';
import { FormsModule } from '@angular/forms';
import { ContactDisplayComponent } from './m-components/contacts/contact-display/contact-display.component';
import { AttachmentComponent } from './m-components/compose/attachment/attachment.component';
import { Attachment2Component } from './m-components/messaege/attachment2/attachment2.component';
import { MessaegeComponent } from './m-components/messaege/messaege.component';

@NgModule({
  declarations: [
    MainScreenComponent,
    InboxComponent,
    TrashComponent,
    DraftComponent,
    ComposeComponent,
    ContactsComponent,
    SentComponent,
    EmailButtonComponent,
    ShowFoldersComponent,
    FoldersComponent,
    CreateFolderComponent,
    Button2Component,
    AddContactComponent,
    Button3Component,
    ContactDisplayComponent,
    AttachmentComponent,
    Attachment2Component,
    MessaegeComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    RouterModule,
    FontAwesomeModule,
    MainScreenRoutingModule,
    NgxPaginationModule
  ]
})
export class MainScreenModule { }
