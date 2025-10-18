import {
  GreetingCard,
  GreetingCardContent,
} from "@/components/example/greeting/greeting";
import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography-h1";

export default function GreetingPage() {
  return (
    <AppPage>
      <TypographyH1>API Greeting Demo</TypographyH1>
      <div className="flex flex-col gap-8">
        <GreetingCard
          title="Default Greeting"
          description="API: GET /v1/greeting/hello"
        >
          <GreetingCardContent />
        </GreetingCard>

        <GreetingCard
          title="Personalized Greeting"
          description="API: GET /v1/greeting/hello?name=KeM198"
        >
          <GreetingCardContent name="KeM198" />
        </GreetingCard>
      </div>
    </AppPage>
  );
}
