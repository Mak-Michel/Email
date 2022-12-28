import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InboxComponent } from './m-components/inbox/inbox.component';
import { TrashComponent } from './m-components/trash/trash.component';
import { DraftComponent } from './m-components/draft/draft.component';
import { StarredComponent } from './m-components/starred/starred.component';
import { ComposeComponent } from './m-components/compose/compose.component';
import { ContactsComponent } from './m-components/contacts/contacts.component';
import { SentComponent } from './m-components/sent/sent.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MainScreenRoutingModule } from './main-screen-routing.module';
import { MainScreenComponent } from './main-screen.component';


@NgModule({
  declarations: [
    MainScreenComponent,
    InboxComponent,
    TrashComponent,
    DraftComponent,
    StarredComponent,
    ComposeComponent,
    ContactsComponent,
    SentComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FontAwesomeModule,
    MainScreenRoutingModule
  ]
})
export class MainScreenModule { }
