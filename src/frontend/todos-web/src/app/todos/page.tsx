import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography-h1";
import { TodosClient } from "@/features/todos/todos-client";

export default function TodosPage() {
  return (
    <AppPage>
      <TypographyH1>Todos</TypographyH1>
      <TodosClient />
    </AppPage>
  );
}
